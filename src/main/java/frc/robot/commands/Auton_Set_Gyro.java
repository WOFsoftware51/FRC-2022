package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

//import java.util.function.Double;

public class Auton_Set_Gyro extends CommandBase 
{
  
    //private final Drive_Auton drivetrain;
    private int init_counter = 0; 
    private double m_angle;

    private final DrivetrainSubsystem m_drivetrainSubsystem;

    

    public Auton_Set_Gyro(DrivetrainSubsystem drivetrainSubsystem, double angle)
      {
        this.m_angle = angle;
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        addRequirements(m_drivetrainSubsystem);
      }

      @Override
      public void initialize() 
      {
        
        m_drivetrainSubsystem.setGyroscope(m_angle);
        m_drivetrainSubsystem.zeroDriveEncoders();
        init_counter = 0; 
      }

    @Override
    public void execute() 
    {
      init_counter++; 
    }
       


    @Override
    public void end(boolean interrupted) 
    {
      m_drivetrainSubsystem.zeroDriveEncoders();
      m_drivetrainSubsystem.setGyroscope(m_angle);

        // Stop the drivetrain
    }

    @Override
    public boolean isFinished() 
    { 
      if(init_counter>=5) 
      { 
        return true;
      } 
        else 
      {
       return false;
      }
    }
}