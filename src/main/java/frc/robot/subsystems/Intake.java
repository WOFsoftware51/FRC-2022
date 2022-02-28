// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase 
{
  private final  WPI_VictorSPX _Intake_1 =  new  WPI_VictorSPX(Constants.Intake_Motor_1);
  private final  WPI_VictorSPX _Intake_2 =  new  WPI_VictorSPX(Constants.Intake_Motor_2);
  private final DoubleSolenoid Intake_Solenoid = new DoubleSolenoid(2, PneumaticsModuleType.CTREPCM, 6, 7);

  
  public void intake_init() 
  {
    _Intake_1.setNeutralMode(NeutralMode.Coast);
    _Intake_1.setInverted(false);
    _Intake_2.setNeutralMode(NeutralMode.Coast);
    _Intake_2.setInverted(false);
  }

  /** Creates a new Intake. */
  public void Intake_In()
  {
    _Intake_1.set(ControlMode.PercentOutput,1);
    _Intake_2.set(ControlMode.PercentOutput,1);
  }
  public void Intake_Out()
  {
    _Intake_1.set(ControlMode.PercentOutput,-0.5);
    _Intake_2.set(ControlMode.PercentOutput,0);
  }
  public void Intake_Off()
  {
    _Intake_1.set(ControlMode.PercentOutput,0);
    _Intake_2.set(ControlMode.PercentOutput,0);
  }


  public void Intake_Deploy()
  {
    Intake_Solenoid.set(Value.kForward);
  }
  public void Intake_Retract()
  {
    Intake_Solenoid.set(Value.kReverse);
  }
  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
}
