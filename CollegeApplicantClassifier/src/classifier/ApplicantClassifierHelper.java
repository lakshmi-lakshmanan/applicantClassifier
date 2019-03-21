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

		instantReject = checkFirstLetterCase(instantReject, applicant, applicant.getFirstName(),
				Constants.INSTANT_REJECT_FIRST_NAME_UPPER_CASE);

		instantReject = checkFirstLetterCase(instantReject, applicant, applicant.getLastName(),
				Constants.INSTANT_REJECT_LAST_NAME_UPPER_CASE);

		instantReject = checkRemainingLettersCase(instantReject, applicant.getFirstName(), applicant,
				Constants.INSTANT_REJECT_FIRST_NAME_LOWER);

		instantReject = checkRemainingLettersCase(instantReject, applicant.getLastName(), applicant,
				Constants.INSTANT_REJECT_LAST_NAME_LOWER);

		return instantReject;
	}

	/**
	 * Checks for lower case of the chars from index 1
	 * 
	 * @param instantReject
	 * @param nameChars
	 * @param applicant
	 * @param reason
	 * @return Boolean
	 */
	private Boolean checkRemainingLettersCase(Boolean instantReject, String nameChars, Applicant applicant,
			String reason) {
		if (instantReject != Boolean.TRUE && nameChars != null) {
			String subName = nameChars.substring(1, nameChars.length());
			if (!subName.equals(subName.toLowerCase())) {
				instantReject = Boolean.TRUE;
				applicant.setReason(reason);
			}
		}
		return instantReject;
	}

	/**
	 * Checks for upper case for first index.
	 * 
	 * @param applicant
	 * @param nameString
	 * @param reason
	 * @return Boolean
	 */
	private Boolean checkFirstLetterCase(Boolean instantReject, Applicant applicant, String nameString, String reason) {
		if (nameString != null && !Character.isUpperCase(nameString.charAt(0))) {
			instantReject = Boolean.TRUE;
			applicant.setReason(reason);
		}
		return instantReject;
	}
}
