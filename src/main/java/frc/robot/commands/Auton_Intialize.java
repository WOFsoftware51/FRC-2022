package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

//import java.util.function.Double;

public class Auton_Intialize extends CommandBase 
{
  
    //private final Drive_Auton drivetrain;
    private int init_counter = 0; 

    private final DrivetrainSubsystem m_drivetrainSubsystem;
    private final Intake m_intake;

    

    public Auton_Intialize(DrivetrainSubsystem drivetrainSubsystem, Intake intake)
      {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.m_intake = intake;
        addRequirements(m_drivetrainSubsystem);
        addRequirements(m_intake);
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
      m_intake.Intake_Out();
        //rotationPercent = 0;
    }
       


    @Override
    public void end(boolean interrupted) 
    {
      m_drivetrainSubsystem.zeroDriveEncoders();
      m_drivetrainSubsystem.zeroGyroscope();
      m_intake.Intake_Off();

        // Stop the drivetrain
    }

    @Override
    public boolean isFinished() 
    { 
      if(init_counter>=25) 
      { 
        return true;
      } 
        else 
      {
       return false;
      }
    }
}