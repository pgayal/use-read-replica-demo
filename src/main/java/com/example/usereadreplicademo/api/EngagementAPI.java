package com.example.usereadreplicademo.api;

import com.example.usereadreplicademo.dto.EngagementDTO;
import com.example.usereadreplicademo.service.EngagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller
 *
 * @author pgayal
 * created on 02/20/2022
 */
@RestController
@RequestMapping("/api")
public class EngagementAPI {

    final EngagementService engagementService;

    @Autowired
    public EngagementAPI(EngagementService engagementService) {
        this.engagementService = engagementService;
    }

    @PostMapping("/engagements")
    public ResponseEntity<EngagementDTO> saveEngagements(@RequestBody EngagementDTO engagementDTO) {
        EngagementDTO saved = engagementService.saveEngagements(engagementDTO);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/engagements")
    public ResponseEntity<List<EngagementDTO>> getEngagements() {
        List<EngagementDTO> engagements = engagementService.getEngagements();
        return ResponseEntity.ok(engagements);
    }

}
