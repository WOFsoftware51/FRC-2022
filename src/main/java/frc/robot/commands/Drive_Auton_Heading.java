package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


//import java.util.function.Double;

public class Drive_Auton_Heading extends CommandBase 
{
  
    //private final Drive_Auton drivetrain;
    private final Double m_translationX;
    private final Double m_translationY;
    private final Double m_angle;
    private final Double m_distance;
    private  Double turn_error;
    private  Double accel = 0.0;
    private  Double yaw;
    private double translationXPercent = 0;
    private double translationYPercent = 0;
    private double rotationPercent = 0;
    private final DrivetrainSubsystem m_drivetrainSubsystem;
    private Boolean EndCommand = false;
    private Boolean stopped = false;

    

    public Drive_Auton_Heading(DrivetrainSubsystem drivetrainSubsystem,Double translationX, Double translationY, Double angle, Double distance, Boolean stopped)
      {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.m_translationX = translationX;
        this.m_translationY = translationY;
        this.m_angle = angle;
        this.m_distance = distance;
        this.stopped = stopped;
        
        addRequirements(m_drivetrainSubsystem);
      }

      @Override
      public void initialize() 
      {
        if(stopped)
        {
          accel = 0.0;
        }
        else
        {
          accel = 1.0;
        }
        EndCommand = false;
        m_drivetrainSubsystem.zeroDriveEncoders();
      }

    @Override
    public void execute() 
    {
    
        yaw = m_drivetrainSubsystem.getGyroscopeAngle();
        turn_error = yaw - m_angle;
        //rotationPercent = 0;
        SmartDashboard.putNumber("Distance",m_drivetrainSubsystem.Distance);
        SmartDashboard.putNumber("Yaw",yaw);
        SmartDashboard.putNumber("turn_error",turn_error);

      if(turn_error<-30)
      {
        rotationPercent = 0.3;
      }
        else if(turn_error<-10)
      {
        rotationPercent = 0.15;
      }
        else if(turn_error<-1)
      {
        rotationPercent = 0.05;
      }
        else if(turn_error>30)
      {
        rotationPercent = -0.3;
      }
        else if(turn_error>10)
      {
        rotationPercent = -0.15;
      }
        else if(turn_error>1)
      {
        rotationPercent = -0.05;
      }
        else
      {
        rotationPercent = 0.0;
      }

      if(accel<1.0)
      {
        accel = accel + 0.04;
        translationXPercent = m_translationX * accel;
        translationYPercent = m_translationY * accel;
        
      }
      
      if(m_drivetrainSubsystem.Distance>m_distance)
      {
        translationXPercent = 0;
        translationYPercent = 0;
        rotationPercent = 0;
 
          EndCommand=true;
      }
      else if(m_drivetrainSubsystem.Distance> 1500 + m_distance)
      {
        translationXPercent = m_translationX * 0.5;
        translationYPercent = m_translationY * 0.5;
      }
      else
      {
        translationXPercent = m_translationX;
        translationYPercent = m_translationY;
      }
       
  
      m_drivetrainSubsystem.drive(
        ChassisSpeeds.fromFieldRelativeSpeeds
        (
          translationXPercent * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
          translationYPercent * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
          rotationPercent * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND,
          m_drivetrainSubsystem.getGyroscopeRotation()
        ));
      
      
    }

    @Override
    public void end(boolean interrupted) 
    {
      m_drivetrainSubsystem.zeroDriveEncoders();

      m_drivetrainSubsystem.drive(ChassisSpeeds.fromFieldRelativeSpeeds(0.0, 0.0, 0.0, m_drivetrainSubsystem.getGyroscopeRotation()));
        // Stop the drivetrain
    }

    @Override
    public boolean isFinished() 
    { 
      return EndCommand;
    }

}