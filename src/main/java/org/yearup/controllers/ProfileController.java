package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;

import java.security.Principal;

@RestController
@RequestMapping("profile")
@CrossOrigin
public class ProfileController {

    private final ProfileDao profileDao;
    private final UserDao userDao; // Declare UserDao field

    // Constructor injection for ProfileDao and UserDao
    public ProfileController(ProfileDao profileDao, UserDao userDao) {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @GetMapping
    public ResponseEntity<Profile> getProfile(Principal principal) {
        try {
            // Fetch the userId using the username from Principal
            String username = principal.getName();
            int userId = userDao.getIdByUsername(username);

            if (userId == -1) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
            }

            Profile profile = profileDao.getByUserId(userId);

            if (profile == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving profile.", e);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateProfile(Principal principal, @RequestBody Profile profile) {
        try {
            // Fetch the userId using the username from Principal
            String username = principal.getName();
            int userId = userDao.getIdByUsername(username);

            if (userId == -1) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
            }

            profile.setUserId(userId);
            profileDao.update(profile);

            return ResponseEntity.ok("Profile updated successfully.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating profile.", e);
        }
    }
}
