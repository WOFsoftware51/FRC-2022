// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class AutonSelectorCommand extends CommandBase {
  /** Creates a new AutonSelectorCommand. */


  private final SendableChooser<Integer> a_chooser = new SendableChooser<>();

  public AutonSelectorCommand() 
  {
    


    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {

    SmartDashboard.putData("Auton Selector", a_chooser);
    a_chooser.setDefaultOption("Move",1);
    a_chooser.addOption("1 Ball", 2);
    a_chooser.addOption("2 Ball", 3);
    a_chooser.addOption("3 Ball", 4);
    a_chooser.addOption("4 Ball", 5);

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
