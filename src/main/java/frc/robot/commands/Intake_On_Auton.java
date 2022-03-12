// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class Intake_On_Auton extends CommandBase 
{
  private Boolean EndCommand = false;
  private final Intake m_intake;
  private int intake_counter = 0; 

  /** Creates a new Intake_Auton. */
  public Intake_On_Auton(Intake intake) 
  {
    this.m_intake = intake;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    intake_counter = 0;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    EndCommand= true;
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
    return EndCommand;
  }
}
