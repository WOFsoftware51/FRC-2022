// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter  ;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class ShootCommand extends CommandBase 
{
  /** Creates a new ShootCommand. */
 
   public static double speedShoot;
  
   
  private final Shooter m_shooter;
  //private final SendableChooser<Integer> s_chooser = new SendableChooser<>();
  public ShootCommand(Shooter shooter, Integer shotSpeed)

  {
      this.m_shooter = shooter;
      addRequirements(shooter);
      speedShoot = shotSpeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    /*
    SmartDashboard.putData("Shot Speed", s_chooser);
    s_chooser.setDefaultOption("4500",15360);
    s_chooser.addOption("Bumper", 7500);
    s_chooser.addOption("4100", 13995);
    s_chooser.addOption("4200", 14336);
    s_chooser.addOption("4300", 14677);
    s_chooser.addOption("4400", 15019);
    s_chooser.addOption("4600", 15701);
    s_chooser.addOption("4700", 16043);
    */
    m_shooter.shooter_init();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    SmartDashboard.putNumber("Encoder", m_shooter.encoder());
    SmartDashboard.putNumber("Speed", m_shooter.speed());

    //speedShoot = s_chooser.getSelected();
    m_shooter.shooter_on(speedShoot);

    //m_shooter.shooter_off();
    //this.cancel();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    m_shooter.shooter_off();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return false;
  }
}
