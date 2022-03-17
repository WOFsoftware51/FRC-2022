package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

//import java.util.function.Double;

public class Auton_Intialize extends CommandBase 
{
  
    //private final Drive_Auton drivetrain;
    private int init_counter = 0; 

    private final DrivetrainSubsystem m_drivetrainSubsystem;

    

    public Auton_Intialize(DrivetrainSubsystem drivetrainSubsystem)
      {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        addRequirements(m_drivetrainSubsystem);
      }

      @Override
      public void initialize() 
      {
        
        m_drivetrainSubsystem.zeroGyroscope();
        m_drivetrainSubsystem.zeroDriveEncoders();
        init_counter = 0; 
      }

    @Override
    public void execute() 
    {
      init_counter++; 
        //rotationPercent = 0;
    }
       


    @Override
    public void end(boolean interrupted) 
    {
      m_drivetrainSubsystem.zeroDriveEncoders();
      m_drivetrainSubsystem.zeroGyroscope();
        // Stop the drivetrain
    }

    @Override
    public boolean isFinished() 
    { 
      if(init_counter>=1) 
      { 
        return true;
      } 
        else 
      {
       return false;
      }
    }
}