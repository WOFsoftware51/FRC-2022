// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider usin GetAbsolutePosition this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class One_Ball_Auto extends SequentialCommandGroup 
{
  /** Creates a new One_Ball_Auto. */
  public One_Ball_Auto(Shooter m_shooter, DrivetrainSubsystem m_drivetrain, Intake m_intake) 
  {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelCommandGroup(new Auton_Intialize(m_drivetrain,m_intake),  new Shoot_Auton(m_shooter,60)),
      new ParallelRaceGroup(new Intake_On_Auton(m_intake, 200), new Drive_Auton_Heading(m_drivetrain, 0.3, 0.0, 0.0, 94000.0, true)), //127-bot"
      new Auton_Step(1)
    );
  }
}
