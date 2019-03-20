package classifier;

import domain.Applicant;
import domain.Constants;
import domain.ReviewOutcomeType;

/**
 * This class reviews an applicant for instant acceptance, instant rejection and
 * further review required by admin.
 * 
 * @author
 *
 */
public class ApplicantClassifier extends ApplicantClassifierHelper implements ApplicantClassifierInterface {

	public Applicant getApplicantReview(Applicant applicant) {
		if (applicant != null) {
			/*
			 * This method calculates the percentage of GPA based on scale.
			 */
			Double percentageScore = calculatePercentageGpa(applicant.getGpaScore(), applicant.getMaximumScore());

			if (checkForInstantReject(applicant, percentageScore)) {
				applicant.setVerdict(ReviewOutcomeType.INSTANT_REJECT.verdict);

			} else if (checkForInstantAccept(applicant, percentageScore)) {
				applicant.setVerdict(ReviewOutcomeType.INSTANT_ACCEPT.verdict);

			} else {
				applicant.setVerdict(ReviewOutcomeType.FURTHER_REVIEW.verdict);
			}

		}
		return applicant;

	}

	/**
	 * This method check for instant accept criteria against State, age, GPA and
	 * SAT/ACT score.
	 * 
	 * @param applicant
	 * @param percentageScore
	 * @return Boolean
	 */
	public Boolean checkForInstantAccept(Applicant applicant, Double percentageScore) {
		Boolean instantAccept = Boolean.FALSE;
		if ((((applicant.getState() != null && applicant.getState().equals("California"))
				&& (applicant.getAge() >= 17 && applicant.getAge() < 26)) || (applicant.getAge() > 80))
				&& percentageScore > Constants.INSTANT_ACCEPT_GPA_SCORE
				&& (applicant.getSatScore() > 1920 || applicant.getActScore() > 27)) {
			instantAccept = Boolean.TRUE;
		}
		return instantAccept;
	}

	/**
	 * Check for Instant Reject cases against felony, age, and GPA.
	 * 
	 * @param applicant
	 * @param percentageScore
	 * @return Boolean
	 */
	public Boolean checkForInstantReject(Applicant applicant, Double percentageScore) {
		Boolean instantReject = Boolean.FALSE;
		if (applicant.getNumberOfFelony() >= 1) {
			instantReject = Boolean.TRUE;
			applicant.setReason(Constants.INSTANT_REJECT_FELONY_REASON);
		} else if (percentageScore != null && percentageScore < Constants.INSTANT_REJECT_GPA_SCORE) {
			instantReject = Boolean.TRUE;
			applicant.setReason(Constants.INSTANT_REJECT_GPA_LESS);
		} else if (!(applicant.getAge() > 0)) {
			instantReject = Boolean.TRUE;
			applicant.setReason(Constants.INSTANT_REJECT_AGE_INVALID);
		} else {
			instantReject = checkPatternforNames(applicant);

		}
		return instantReject;
	}

}
