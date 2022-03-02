// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;


public class Intake_Command extends CommandBase 
{
  Boolean in_1;
  private final Intake m_intake;
  public Intake_Command(Intake intake,Boolean in)
  {
    this.m_intake = intake;
    addRequirements(intake);
    in_1=in;
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    m_intake.Intake_Deploy();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    if (in_1)
    {
      m_intake.Intake_In();
    }
    else
    {
      m_intake.Intake_Out();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    m_intake.Intake_Off();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return false;
  }
}
