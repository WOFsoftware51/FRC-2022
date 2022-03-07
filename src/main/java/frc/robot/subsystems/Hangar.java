// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;

public class Hangar extends SubsystemBase
 {
  private final TalonSRX hangar_master =  new  TalonSRX(Constants.HANGAR_MASTER_MOTOR);
  private final TalonSRX hangar_follower =  new  TalonSRX(Constants.HANGAR_FOLLOWER_MOTOR);
  private final DoubleSolenoid claw_1 = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 0, 1);
  private final DoubleSolenoid claw_2 = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 2, 3);
  private final DoubleSolenoid claw_3 = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 4, 5);
  private final DoubleSolenoid claw_4 = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 6, 7);
  public DigitalInput uno_limitSwitch = new DigitalInput(9);
  public DigitalInput dos_limitSwitch = new DigitalInput(8);
  public boolean White_Claw_Release = false;


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
  }
    
  /** Releases the hatch. */
  public void hangar_off() 
  {
    hangar_master.set(ControlMode.PercentOutput,0);
  }


  public void claw1_close()
  {
      claw_1.set(Value.kReverse);
  }

  public void claw2_close()
  {
      claw_2.set(Value.kReverse);
  }

  public void claw3_close()
  {
      claw_3.set(Value.kReverse);
  }

  public void claw4_close()
  {
    claw_4.set(Value.kReverse);
  }

  public void claw1_open()
  {
    claw_1.set(Value.kForward);
  }

  public void claw2_open()
  {
    claw_2.set(Value.kForward);
  }

  public void claw3_open()
  {
    claw_3.set(Value.kForward);
  }

  public void claw4_open()
  {
    claw_4.set(Value.kForward);
  }


  public void claw1_latch()
  {
    if (dos_limitSwitch.get())
    {
      claw_1.set(Value.kForward);
    }
    else
    {
      claw_1.set(Value.kReverse);
    }
  }
  
  public void claw3_latch()
  {
    if (dos_limitSwitch.get())
    {
      claw_3.set(Value.kForward);
    }
    else
    {
      claw_3.set(Value.kReverse);
    }
  }
  
  public double arm_encoder_position()
  {
    double arm_encoder = hangar_master.getSelectedSensorPosition();
    return arm_encoder;
  }
}