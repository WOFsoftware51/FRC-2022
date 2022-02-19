// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Hangar;

public class Hangar_Abort_Command extends CommandBase 
{
  private final Hangar m_hangar;
  /** Creates a new Hangar_Ready_Command. */
  public Hangar_Abort_Command(Hangar hangar) 
  {
    this.m_hangar = hangar;
    addRequirements(hangar);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    m_hangar.hangar_off();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    m_hangar.hangar_off();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    m_hangar.hangar_off();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  { 
    return false;
  }
}