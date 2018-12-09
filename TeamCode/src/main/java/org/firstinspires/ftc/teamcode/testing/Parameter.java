package org.firstinspires.ftc.teamcode.testing;

interface Parameter {
	void inc();

	void dec();

	void appendSummary(StringBuilder builder);

	void showDetails(org.firstinspires.ftc.robotcore.external.Telemetry telemetry);
}
