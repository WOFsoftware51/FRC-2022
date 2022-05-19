// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Auton_Preload_Ball extends CommandBase 
{
  /** Creates a new ShootCommand. */
 
   public static boolean ballLoaded;  
   private int counter = 0; 
   private int wait_length = 0; 
   
  private final Shooter m_shooter;
  public Auton_Preload_Ball(Shooter shooter, int wait)

  {
      this.m_shooter = shooter;
      addRequirements(shooter);
      wait_length = wait;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    counter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {

    counter++;
    //SmartDashboard.putData(m_shooter.ballSensor.get());
    if(m_shooter.ballSensor.get())
    {
      m_shooter.ball_loaded();

    }
    else
    {
      m_shooter.load_ball();
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    counter = 0;
    m_shooter.ball_loaded();
  }

  // Returns true when  the command should end.
  @Override
  public boolean isFinished() 
  {
    if(counter>=wait_length) 
    { 
      return true;
    } 
      else 
    {
     return false;
    } 
   }
}
