/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gpacalculatorapp;

import java.util.Scanner;
import java.util.ArrayList;
import com.mycompany.gpacalculatorapp.Models.CourseInputModel;
import com.mycompany.gpacalculatorapp.Models.CourseModel;


/**
 *
 * @author bomot
 */
public class GpaCalculatorApp {
    
    private static final Scanner prompt = new Scanner(System.in);

    public static void main(String[] args) {
        
        GradingLogic Grading = new GradingLogic();
        
        System.out.println("Welcome to the Universal GPA Calculation portal for any number of courses!");
        
        System.out.println("Please enter student name:");
        
        String student_name = prompt.next();
        student_name = student_name.toUpperCase();
        
        // initialize an arraylist of course models to collect each course data provided by user
        ArrayList<CourseModel> AllCourses = new ArrayList<>();    
        
        // get and store course data provided by user into the course variable 
        CourseInputModel course = Grading.GetCourseDetails();
        
        // compute grade based on course data provided by user
        CourseModel full_course_details = Grading.ComputeCourseGrade(course);
        
        // append course data into the arraylist initialized earlier 
        Grading.CollateAllCourses(AllCourses, full_course_details);
        
        // prompt user to provide more course data or terminate program
        System.out.println("Would you like to add more courses? Yes/No");
        
        String user_response = prompt.next();
        
        user_response = user_response.toUpperCase();
        
        // validate user input to continue to add more courses
        while(user_response.equals("YES")){
            course = Grading.GetCourseDetails();
            full_course_details = Grading.ComputeCourseGrade(course);
            Grading.CollateAllCourses(AllCourses, full_course_details);
            
            System.out.println("Would you like to add more courses? Yes/No");
            user_response = prompt.next();      
            user_response = user_response.toUpperCase();
        }
        
        // initialize and compute GPA
        double Gpa = Grading.CalculateGpa(AllCourses);
        
        System.out.println("Dear " + student_name + ",");
        System.out.println("Below is a breakdown of your grades and final GPA.");
        
        System.out.println("CourseName\tCourseCode\tCourseUnit\tCourseGrade\tGradeUnit\t");
        for(CourseModel course_instance: AllCourses){
            System.out.printf("\n%s\t\t%s\t\t%d\t\t%c\t\t%s\t",course_instance.CourseName,course_instance.CourseCode,course_instance.CourseUnit,course_instance.CourseGrade,course_instance.GradeUnit);                     
        }
        
        
        System.out.printf("\nYour GPA is %.2f to 2 decimal places", Gpa);
    }
}
