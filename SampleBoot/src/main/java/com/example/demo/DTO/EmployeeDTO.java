package com.example.demo.DTO;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    private int id;
    private String name;
    private int age;
    private String emailAddress;
    private int projectId;

    //	@NotBlank(message="Name cannot be blank for an employee", groups = {UpdateDetailsValidator.class})

//	@Min(value=20, message="Minimum age should be 20", groups = {CreateDetailsValidator.class})
//	@Max(value = 100, message="Max age should be 100", groups = {CreateDetailsValidator.class})

//	@Email
//	@NotBlank(message="Email cant be null", groups = {UpdateDetailsValidator.class})
//	@UniqueEmailValidator(message = "Email must be unique", groups = {CreateDetailsValidator.class, UpdateDetailsValidator.class})

}
