//package com.backendspringproject.ems.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class EmployeeDto {
//    // when we use EmployeeDto class to transfer the data between client and server
//    // So when we build the restful web services, we will use this EmployeeDto as a response for REST API's.
//    private Long id;
//    private String firstName;
//    private String lastName;
//    private String email;
//}
package com.backendspringproject.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long departmentId;
}
