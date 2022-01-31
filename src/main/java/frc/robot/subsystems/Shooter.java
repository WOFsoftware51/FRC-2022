
package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ShootCommand;

/** Hte Falcon that runs the shooter wheel {@link TalonFX}. */
public class Shooter extends SubsystemBase 
{
  private final  WPI_TalonFX _shooter =  new  WPI_TalonFX(Constants.SHOOTER_MOTOR);

  public void shooter_init() 
  {
    _shooter.setNeutralMode(NeutralMode.Coast);
    _shooter.setInverted(false);
    _shooter.config_kF(0, Constants.SHOOTER_F , 30);
    _shooter.config_kP(0, Constants.SHOOTER_P, 30);
    _shooter.config_kI(0, Constants.SHOOTER_I, 30);
    _shooter.config_kD(0, Constants.SHOOTER_D, 30);
    _shooter.setSensorPhase(true);
  }

  /* Grabs the hatch. */
  public void shooter_on() 
  {
    _shooter.set(ControlMode.Velocity,Constants.SPEED);
    //_shooter.set(ControlMode.Velocity,11000);

  }

  public double encoder() 
  {
    return _shooter.getSelectedSensorPosition();
  }
  
  public double speed()
  {
    return (_shooter.getSelectedSensorVelocity()/2048)*600;
  }

  /** Releases the hatch. */
  public void shooter_off() 
  {
    _shooter.set(ControlMode.PercentOutput, 0);
  }
}