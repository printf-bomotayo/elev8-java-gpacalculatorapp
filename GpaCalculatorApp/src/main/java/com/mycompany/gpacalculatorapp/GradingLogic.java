/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gpacalculatorapp;

import com.mycompany.gpacalculatorapp.Models.CourseModel;
import com.mycompany.gpacalculatorapp.Models.CourseInputModel;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author bomot
 */
public class GradingLogic {
    
    // Method to append individual course details received from the user inside a collection of ArrayList
    public void CollateAllCourses(ArrayList<CourseModel> AllCourses, CourseModel course){
        
        AllCourses.add(course);
    }
    
    // Method to compute course grade and grade unit based on course details received from user 
    // Method returns an object of from class CourseModel
    public CourseModel ComputeCourseGrade(CourseInputModel course_details) {
        
        CourseModel course_grade_details = new CourseModel();
        
        Character course_grade = 'A';
        if(course_details.course_score <= 39) {
            course_grade = 'F';
        }
        else if(course_details.course_score >= 40 && course_details.course_score <= 44){
            course_grade = 'E';
        }
        else if(course_details.course_score >= 45 && course_details.course_score <= 49){
            course_grade = 'D';
        }
        else if(course_details.course_score >= 50 && course_details.course_score <= 59){
            course_grade = 'C';
        }
        else if(course_details.course_score >= 60 && course_details.course_score <= 69){
            course_grade = 'B';
        }
        else if(course_details.course_score >= 70){
            course_grade = 'A';
        }
        
        int course_grade_unit = 1;
        
        if(course_grade.equals('A')) {
            course_grade_unit = 5;
        }
        else if (course_grade.equals('B')) {
            course_grade_unit = 4;
        }
        else if (course_grade.equals('C')) {
            course_grade_unit = 3;
        }
        else if (course_grade.equals('D')) {
            course_grade_unit = 2;
        }
        else if (course_grade.equals('E')) {
            course_grade_unit = 1;
        }
        else if (course_grade.equals('F')) {
            course_grade_unit = 0;
        }
        
        
        course_grade_details.CourseName = course_details.course_name;
        course_grade_details.CourseCode = course_details.course_code;
        course_grade_details.CourseUnit = course_details.course_unit;
        course_grade_details.CourseScore = course_details.course_score;
        course_grade_details.CourseGrade = course_grade;
        course_grade_details.GradeUnit = course_grade_unit;
        
        return course_grade_details;       
    }
    
    
    // Method to collect course details by prompting user
    public CourseInputModel GetCourseDetails(){
        CourseInputModel course_details = new CourseInputModel();
        
        Scanner prompt = new Scanner(System.in);
        
        System.out.println("Enter course name: ");
        String course_name = prompt.nextLine();
        course_name = course_name.toUpperCase();
        
        System.out.println("Enter course code: ");
        String course_code = prompt.nextLine();
        course_code = course_code.replace(" ", "").toUpperCase();
        
        //Validate unit input provided by user  
        int course_unit = 11;
        boolean isValidUnit = false;
        do {
            try {
                System.out.println("Please enter a valid course unit between 1-10: ");
                course_unit = prompt.nextInt();

                if (course_unit >= 1 && course_unit <= 10) {
                    isValidUnit = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid course unit between 1-10.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer btw 1-10.");
                prompt.next(); // Consume the invalid input
            }
        } while (!isValidUnit);  
        
        // Validate score input provided by user
        int course_score = 101;
        boolean isValidScore = false;
        do {
            try {
                System.out.println("Please enter a valid course score between 1-100: ");
                course_score = prompt.nextInt();

                if (course_score >= 1 && course_score <= 100) {
                    isValidScore = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid course score between 1-100.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer btw 1-100.");
                prompt.next(); // Consume the invalid input
            }
        } while (!isValidScore);  
        
        course_details.course_name = course_name;
        course_details.course_code = course_code;
        course_details.course_unit = course_unit;
        course_details.course_score = course_score;
        
        return course_details;   
    }
    
    // Method to calculate GPA by taking a collection of CourseModel objects as arguments
    public double CalculateGpa(ArrayList<CourseModel> AllCourses){
        double Gpa;
        
        ArrayList<Integer> quality_points = new ArrayList<>();
        ArrayList<Integer> course_units = new ArrayList<>();
        
        for(CourseModel course: AllCourses){
            quality_points.add(course.CourseUnit * course.GradeUnit);
            course_units.add(course.CourseUnit);
        }
        
        double total_quality_points = addArrayItems(quality_points);
        double total_course_units = addArrayItems(course_units);
        
        Gpa = total_quality_points / total_course_units;
        
        return Gpa;
    }
    
    // Method to sum all items in an ArrayList of integers
    private static double addArrayItems(ArrayList<Integer> list) {
        double sum = 0;

        // Iterate through the elements and accumulate the sum
        for (int number : list) {
            sum += number;
        }
        return sum;
    }
    
}
