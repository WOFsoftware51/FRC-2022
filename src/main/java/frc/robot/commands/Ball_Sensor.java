// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class Ball_Sensor extends CommandBase 
{
  
  /** Creates a new ShootCommand. */

  private final Shooter m_shooter;

  
  public Ball_Sensor(Shooter shooter)
  {
      this.m_shooter = shooter;
      addRequirements(shooter);
      
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    m_shooter.shooter_init();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    //m_shooter.shooter_off();
    //this.cancel();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  { 
    if((m_shooter.ballSensor.get())) 
    { 
      return true;
    } 
      else 
    {
     return false;
    }
  }
}