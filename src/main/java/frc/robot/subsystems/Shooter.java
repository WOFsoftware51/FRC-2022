
package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Hte Falcon that runs the shooter wheel {@link TalonFX}. */
public class Shooter extends SubsystemBase 
{
  private final  WPI_TalonFX _shooter =  new  WPI_TalonFX(Constants.SHOOTER_MOTOR, Constants.CANIVORE_NAME);
  private final  WPI_VictorSPX _transfer_roller =  new  WPI_VictorSPX(Constants.TRANSFER_ROLLER_MOTOR);

  public void shooter_init() 
  {
    _shooter.setNeutralMode(NeutralMode.Coast);
    _shooter.setInverted(false);
    _shooter.config_kF(0, Constants.SHOOTER_F , 30);
    _shooter.config_kP(0, Constants.SHOOTER_P, 30);
    _shooter.config_kI(0, Constants.SHOOTER_I, 30);
    _shooter.config_kD(0, Constants.SHOOTER_D, 30);
    _shooter.setSensorPhase(true);
    _shooter.setStatusFramePeriod(1, 20);
  }

  /* Grabs the hatch. */
  public void shooter_on(double speed) 
  {
    _shooter.set(ControlMode.Velocity,speed);
    if(_shooter.getSelectedSensorVelocity() > speed - 500)
    {
      _transfer_roller.set(ControlMode.PercentOutput,-1.0);
    }
    else
    {
      _transfer_roller.set(ControlMode.PercentOutput,0.0);
    }
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
    _transfer_roller.set(ControlMode.PercentOutput,0);

  }
}