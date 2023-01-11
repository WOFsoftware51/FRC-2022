package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

//import java.util.function.Double;

public class Auton_Aim_Off extends CommandBase 
{
  
    //private final Drive_Auton drivetrain;

    private final DrivetrainSubsystem m_drivetrainSubsystem;
    private Boolean EndCommand = false;

    

    public Auton_Aim_Off(DrivetrainSubsystem drivetrainSubsystem)
      {
        this.m_drivetrainSubsystem = drivetrainSubsystem;

        addRequirements(m_drivetrainSubsystem);
      }

      @Override
      public void initialize() 
      {
        
        EndCommand = false;
        m_drivetrainSubsystem.AIMOff();;
      }

    @Override
    public void execute() 
    {
      m_drivetrainSubsystem.AIMOff();
      EndCommand=true;
      
    }

    @Override
    public void end(boolean interrupted) 
    {
      m_drivetrainSubsystem.AIMOff();
        // Stop the drivetrain
    }

    @Override
    public boolean isFinished() 
    { 
      return EndCommand;
    }
}