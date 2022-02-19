// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hangar;


public class Hangar_Home_Command extends CommandBase 
{
  private final Hangar m_hangar;
  public Hangar_Home_Command(Hangar hangar)
  {
    this.m_hangar = hangar;
    addRequirements(hangar);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    m_hangar.claw1_open();
    m_hangar.claw3_open();
    m_hangar.claw2_open();
    m_hangar.claw4_open();
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    
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
    return false;
  }
}
