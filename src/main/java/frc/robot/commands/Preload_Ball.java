// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Preload_Ball extends CommandBase 
{
  /** Creates a new ShootCommand. */
 
   public static boolean ballLoaded;  
   private Boolean EndCommand = false;
   
  private final Shooter m_shooter;
  public Preload_Ball(Shooter shooter)

  {
      this.m_shooter = shooter;
      addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    EndCommand = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {

    //SmartDashboard.putData(m_shooter.ballSensor.get());
    if(m_shooter.ballSensor.get())
    {
      EndCommand = true;
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
    m_shooter.ball_loaded();
  }

  // Returns true when  the command should end.
  @Override
  public boolean isFinished() 
  {
    return EndCommand;
  }
}
