package classifier;

import domain.Applicant;
import domain.Constants;

public class ApplicantClassifierHelper {

	/**
	 * This method converts gpa to percentage score;
	 * 
	 * @param gpaScore
	 * @param maximumScore
	 */
	protected Double calculatePercentageGpa(Double gpaScore, Double maximumScore) {

		Double percentageScore = null;
		if (maximumScore != null && maximumScore > 0) {
			percentageScore = (gpaScore / maximumScore) * 100;
		}
		return percentageScore;
	}

	/**
	 * Checks for first name and last name conditions. First Character to be upper
	 * case and rest lower case.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	protected Boolean checkPatternforNames(Applicant applicant) {
		Boolean instantReject = Boolean.FALSE;
		char[] charsFirstName = null;
		char[] charsLastName = null;
		if (applicant.getFirstName() != null)
			charsFirstName = applicant.getFirstName().toCharArray();
		if (applicant.getLastName() != null)
			charsLastName = applicant.getLastName().toCharArray();

		if (charsFirstName != null && !(Character.isUpperCase(charsFirstName[Constants.INDEX_FOR_FIRST_NAME_REJECT]))) {
			instantReject = Boolean.TRUE;
			applicant.setReason(Constants.INSTANT_REJECT_FIRST_NAME_UPPER_CASE);
		} else if (charsLastName != null
				&& !(Character.isUpperCase(charsLastName[Constants.INDEX_FOR_LAST_NAME_REJECT]))) {
			instantReject = Boolean.TRUE;
			applicant.setReason(Constants.INSTANT_REJECT_LAST_NAME_UPPER_CASE);
		}

		if (instantReject != Boolean.TRUE && charsFirstName != null) {
			for (int i = 1; i < charsFirstName.length; i++) {
				if (!Character.isLowerCase(charsFirstName[i])) {
					instantReject = Boolean.TRUE;
					applicant.setReason(Constants.INSTANT_REJECT_FIRST_NAME_LOWER + (i + 1));
					break;
				}
			}
		}

		if (instantReject != Boolean.TRUE && charsLastName != null) {
			for (int i = 1; i < charsLastName.length; i++) {
				if (!Character.isLowerCase(charsLastName[i])) {
					instantReject = Boolean.TRUE;
					applicant.setReason(Constants.INSTANT_REJECT_LAST_NAME_LOWER + (i + 1));
					break;
				}
			}
		}
		return instantReject;
	}

}
