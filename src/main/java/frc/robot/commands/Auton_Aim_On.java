package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

//import java.util.function.Double;

public class Auton_Aim_On extends CommandBase 
{
  
    //private final Drive_Auton drivetrain;

    private final DrivetrainSubsystem m_drivetrainSubsystem;
    private Boolean EndCommand = false;
    private int count = 0;

    

    public Auton_Aim_On(DrivetrainSubsystem drivetrainSubsystem)
      {
        this.m_drivetrainSubsystem = drivetrainSubsystem;

        addRequirements(m_drivetrainSubsystem);
      }

      @Override
      public void initialize() 
      {
        
        EndCommand = false;
        count = 0;

        m_drivetrainSubsystem.AIMOn();
      }

    @Override
    public void execute() 
    {
      m_drivetrainSubsystem.AIMOn();
  

        
      if(m_drivetrainSubsystem.tv == 1)
      {
     
          count++;
      }
      
    }

    @Override
    public void end(boolean interrupted) 
    {
        // Stop the drivetrain
    }

    @Override
    public boolean isFinished() 
    { 
      if(count>=50) 
      { 
        return true;
      } 
        else 
      {
       return false;
      }
    }
}