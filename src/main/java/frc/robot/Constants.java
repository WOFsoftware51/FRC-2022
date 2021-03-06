// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants 
{
    
    /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.5842; //  Measure and set trackwidth
    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.5842; //  Measure and set wheelbase

    public static final int DRIVETRAIN_PIGEON_ID = 0; // FIXME Set Pigeon ID

    public static final String CANIVORE_NAME = "CANivore";

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 32; //  Set front left module drive motor ID
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 42; //  Set front left module steer motor ID
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 12; // FIXME Set front left steer encoder ID
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(37.0 + 180.0); //  Me

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 34; //  Set front right drive motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 44; //  Set front right steer motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 14; // FIXME Set front right steer encoder ID
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(15.8); //  Measure and set front right steer offset

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 33;//  Set back left drive motor ID
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 43; //  Set back left steer motor ID
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 13; // FIXME Set back left steer encoder ID
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(114.6 + 180.0); //  Measure and set back left steer offset

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 31; //  Set back right drive motor ID
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 41; //  Set back right steer motor ID
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 11; // FIXME Set back right steer encoder ID
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(78.0); //Measure and set back right steer offset

    ///////51/////////////
    public static final int HANGAR_MASTER_MOTOR = 5; //  
    public static final int HANGAR_FOLLOWER_MOTOR = 6; //  
    public static final int TRANSFER_ROLLER_MOTOR = 7;
    public static final int Intake_Motor_1 = 8;
    public static final int Intake_Motor_2 = 9;

    public static final int SHOOTER_MOTOR = 12; // 

    public static final double SHOOTER_P = 0.3; //  
    public static final double SHOOTER_I = 0.0; //  
    public static final double SHOOTER_D = 0.003; //  
    public static final double SHOOTER_F= 0.051; // 

    public static final int SHOOTER_MOTOR_2 = 13; //

    public static final double SHOOTER_2_P = 0.15; //  
    public static final double SHOOTER_2_I = 0.0; //  
    public static final double SHOOTER_2_D = 0.0015; //  
    public static final double SHOOTER_2_F= 0.051; // 

    public static final double AIM_P =  0.012; //  
    public static final double AIM_I = 0.0; //  
    public static final double AIM_D = 0.0; //  

    public static  final double HANGAR_SPEED= 0.75;
    public static final double DRIVE_SPEED = 0.6;
    

    public static final double AUTON_SHOT_SPEED = 6826;
    
    public static final double SHOOT_RATIO = 1.3;    //1.5
   // public static final double SHOOT_RATIO = 3.5;

}
// 