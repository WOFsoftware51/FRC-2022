// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter  ;
import edu.wpi.first.networktables.NetworkTableInstance;

import java.util.function.DoubleSupplier;


public class ShootCommand extends CommandBase 
{
  /** Creates a new ShootCommand. */
 
   public static double realSpeed;
   public static double speedShoot;
   public static double speedShoot2;
   private final DoubleSupplier m_speedShoot;
   public Double tv = 0.0;
   public Double ty = 0.0;
  
  
  
  private final Shooter m_shooter;
  public ShootCommand(Shooter shooter, DoubleSupplier shotSpeed)

  {
      this.m_shooter = shooter;
      addRequirements(shooter);
      m_speedShoot = shotSpeed;
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
    tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);

    realSpeed = m_speedShoot.getAsDouble();
    realSpeed = realSpeed-(ty*35);
    speedShoot = realSpeed;
    speedShoot2 = speedShoot * Constants.SHOOT_RATIO;
    SmartDashboard.putNumber("Encoder", m_shooter.encoder());
    SmartDashboard.putNumber("ty", ty);

    SmartDashboard.putNumber("Speed", m_shooter.speed());
    SmartDashboard.putNumber("Speed2", m_shooter.speed_2());
    SmartDashboard.putNumber("Speed_target", (speedShoot/2048.0)*600.0);
    SmartDashboard.putNumber("Speed2_target",(speedShoot2/2048.0)*600.0);

    //speedShoot = s_chooser.getSelected();
    m_shooter.shooter_on(speedShoot, speedShoot2);
   // m_shooter.shooter_on(speedShoot, speedShoot2);  //5120

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