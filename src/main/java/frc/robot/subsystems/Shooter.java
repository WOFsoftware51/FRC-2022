
package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Hte Falcon that runs the shooter wheel {@link TalonFX}. */
public class Shooter extends SubsystemBase 
{
  private final  WPI_TalonFX _shooter =  new  WPI_TalonFX(Constants.SHOOTER_MOTOR, Constants.CANIVORE_NAME);
  private final  WPI_VictorSPX _transfer_roller =  new  WPI_VictorSPX(Constants.TRANSFER_ROLLER_MOTOR);
  private final  WPI_TalonFX _shooter_2 =  new  WPI_TalonFX(Constants.SHOOTER_MOTOR_2, Constants.CANIVORE_NAME);
  public DigitalInput ballSensor = new DigitalInput(0);
  public DigitalInput ballSensor2 = new DigitalInput(1);
  private boolean shooting = false;

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

    _shooter_2.setNeutralMode(NeutralMode.Coast);
    _shooter_2.setInverted(true);
    _shooter_2.config_kF(0, Constants.SHOOTER_2_F , 30);
    _shooter_2.config_kP(0, Constants.SHOOTER_2_P, 30);
    _shooter_2.config_kI(0, Constants.SHOOTER_2_I, 30);
    _shooter_2.config_kD(0, Constants.SHOOTER_2_D, 30);
    _shooter_2.setSensorPhase(true);
    _shooter_2.setStatusFramePeriod(1, 20);

    _transfer_roller.setNeutralMode(NeutralMode.Brake);
  }

  public void reset_ball()
  {
    _transfer_roller.set(ControlMode.PercentOutput, 0.2);
  }

  

  public void load_ball()
  {
    _transfer_roller.set(ControlMode.PercentOutput, -0.3);
  }

  public void ball_loaded()
  {
    if (!shooting)
    {
      _transfer_roller.set(ControlMode.PercentOutput, 0.0);
    }
   
  }

  /* Grabs the hatch. */
  public void shooter_on(double speed, double speed_2) 
  {
    shooting = true;
    _shooter.set(ControlMode.Velocity,speed);
    _shooter_2.set(ControlMode.Velocity,speed_2);

    if(_shooter.getSelectedSensorVelocity() > speed - 400 && _shooter_2.getSelectedSensorVelocity() > speed_2 - 400 )
    {
      _transfer_roller.set(ControlMode.PercentOutput,-0.85);
    }
    else
    {
      _transfer_roller.set(ControlMode.PercentOutput,0.0);
    }
    //_shooter.set(ControlMode.Velocity,11000);


  }

  public void shooter_spit() 
  {
    shooting = true;
    _shooter.set(ControlMode.PercentOutput,0.08);
    _shooter_2.set(ControlMode.PercentOutput,0.35);
    _transfer_roller.set(ControlMode.PercentOutput,-0.7);
    //_shooter.set(ControlMode.Velocity,11000);
  }

  public void shoot_low() 
  {
    shooting = true;
    _shooter.set(ControlMode.PercentOutput,0.16);
    _shooter_2.set(ControlMode.PercentOutput,0.4);
    _transfer_roller.set(ControlMode.PercentOutput,-1.0);
    //_shooter.set(ControlMode.Velocity,11000);
  }


  

  public double encoder() 
  {
    return _shooter.getSelectedSensorPosition();
  }
  
  public double speed()
  {
    return (_shooter.getSelectedSensorVelocity()/2048.0)*600.0;
  }

  public double speed_2()
  {
    return (_shooter_2.getSelectedSensorVelocity()/2048.0)*600.0;
  }

  /** Releases the hatch. */
  public void shooter_off() 
  {
    shooting = false;
    _shooter.set(ControlMode.PercentOutput, 0);
    _transfer_roller.set(ControlMode.PercentOutput,0);
    _shooter_2.set(ControlMode.PercentOutput, 0);

  }
}