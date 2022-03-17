// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hangar;


public class HangarCommand extends CommandBase 
{
  /** Creates a new HangarCommand. */
  private final Hangar m_hangar;
  private final DoubleSupplier m_translationXSupplier;
  private Double hangEncoder = 0.0;

  public HangarCommand(Hangar hangar,DoubleSupplier translationXSupplier)
  {
      this.m_hangar = hangar;
      addRequirements(hangar);
      this.m_translationXSupplier = translationXSupplier;
      //hangar.dos_limitSwitch.get();

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    m_hangar.hangar_init();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    hangEncoder = m_hangar.arm_encoder_position();
    SmartDashboard.putNumber("Hanger_Encoder", hangEncoder);
    m_hangar.hangar_on(m_translationXSupplier.getAsDouble());
    if(hangEncoder>30000 && hangEncoder<35000)
    {
      m_hangar.claw1_close();
    }

  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    
    m_hangar.hangar_off();
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return false;
  }
}