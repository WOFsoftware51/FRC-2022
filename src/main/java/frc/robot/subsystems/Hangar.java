// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;

public class Hangar extends SubsystemBase
 {
  private final TalonSRX hangar_master =  new  TalonSRX(Constants.SHOOTER_MOTOR);
  private final TalonSRX hangar_follower =  new  TalonSRX(Constants.SHOOTER_MOTOR);

  public void hangar_init() 
  {
    hangar_master.setNeutralMode(NeutralMode.Brake);
    hangar_follower.setNeutralMode(NeutralMode.Brake);
    hangar_master.setInverted(false);
    hangar_follower.setInverted(false);
    hangar_follower.follow(hangar_master);
  }
  
  /* Grabs the hatch. */
  public void hangar_on(double speed) 
  {
    hangar_master.set(ControlMode.PercentOutput,speed);
       //_hangar.set(ControlMode.Velocity,11000);

  }
    
  /** Releases the hatch. */
  public void hangar_off() 
  {

    hangar_master.set(ControlMode.PercentOutput,0);
  }
}
