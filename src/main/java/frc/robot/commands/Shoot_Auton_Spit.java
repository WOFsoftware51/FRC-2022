// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Shoot_Auton_Spit extends CommandBase 
{
  
  /** Creates a new ShootCommand. */
 
  private final Shooter m_shooter;
  private int shoot_counter = 0; 
  
  public Shoot_Auton_Spit(Shooter shooter)
  {
      this.m_shooter = shooter;
      addRequirements(shooter);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    shoot_counter = 0;
    m_shooter.shooter_init();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    SmartDashboard.putNumber("Encoder", m_shooter.encoder());
    SmartDashboard.putNumber("Counter", shoot_counter);
    shoot_counter++;
    m_shooter.shooter_spit();

    //m_shooter.shooter_off();
    //this.cancel();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    m_shooter.shooter_off();
    shoot_counter = 0;

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  { 
    if(shoot_counter>=150) 
    { 
      return true;
    } 
      else 
    {
     return false;
    }
  }
}