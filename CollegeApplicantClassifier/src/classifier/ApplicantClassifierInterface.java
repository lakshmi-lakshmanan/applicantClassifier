package classifier;

import domain.Applicant;

public interface ApplicantClassifierInterface {

	 Boolean checkForInstantAccept(Applicant applicant, Double percentageScore);
	
	 Boolean checkForInstantReject(Applicant applicant, Double percentageScore);
}
