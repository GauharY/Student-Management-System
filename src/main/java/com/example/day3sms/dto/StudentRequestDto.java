//dto-> similar to moel it is used for showing responses and data 
//dto->data transfer object  //req lena or validate krna
//request used to send data  //mutable
//response used to fetch data  //immutable

package com.example.day3sms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


//@Data  //we are not using data bcz it also giving unnecessary functions but we only need getters and setters
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
public class StudentRequestDto {

    // @Id  //means primary key //we are not requiresd to make id bcz it is auto generated in mongodb
    //private String id;

    @NotBlank(message="Name cannot be blank")
    private String name;
    @Min(value=5,message ="Age cannot be less than 5")
    @Max(value=90, message ="Age cannot be more than 90")
    private int age;

    @Email(message="Email should be valid")
    @NotBlank(message="email cannot be blank")
    private String email;

}
