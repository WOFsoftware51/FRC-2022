package frc.robot.commands;

import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

//import java.util.function.Double;

public class Auton_Step extends CommandBase 
{
  
    //private final Drive_Auton drivetrain;
    private int init_counter = 0; 
    private int wait_length = 0;


    

    public Auton_Step(int wait)
      {
        this.wait_length = wait;
       
      }

      @Override
      public void initialize() 
      {
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
      init_counter = 0; 
        // Stop the drivetrain
    }

    @Override
    public boolean isFinished() 
    { 
      if(init_counter>=wait_length) 
      { 
        return true;
      } 
        else 
      {
       return false;
      }
    }
}