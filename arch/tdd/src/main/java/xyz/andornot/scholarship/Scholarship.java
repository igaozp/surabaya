package xyz.andornot.scholarship;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Scholarship {
    private LocalDate deadline;
}
