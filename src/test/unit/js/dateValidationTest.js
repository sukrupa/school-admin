DateValidationTest = TestCase("DateValidationTest");


var testString;

DateValidationTest.prototype.setUp = function() {
};


DateValidationTest.prototype.tearDown = function() {
};

DateValidationTest.prototype.testValidateCorrectDate = function() {
    var validator = new DateValidator();
    assertEquals(true, validator.validate("12-12-2012", "12:12"));
};

DateValidationTest.prototype.testValidateCorrectDateOneDigitDayMonthHourMinute = function() {
    var validator = new DateValidator();
    assertEquals(true, validator.validate("12-12-2012", "12:12"));
};

DateValidationTest.prototype.testValidateIncorrectDayThreeDigits = function() {
    var validator = new DateValidator();
    assertEquals(false, validator.validate("123-12-2012", "12:12"));
};

DateValidationTest.prototype.testValidateIncorrectDayLetter = function() {
    var validator = new DateValidator();
    assertEquals(false, validator.validate("a-12-2012", "12:12"));
};

DateValidationTest.prototype.testValidateIncorrectMonthThreeDigits = function() {
    var validator = new DateValidator();
    assertEquals(false, validator.validate("12-123-2012", "12:12"));
};

DateValidationTest.prototype.testValidateIncorrectMonthLetter = function() {
    var validator = new DateValidator();
    assertEquals(false, validator.validate("12-a-2012", "12:12"));
};

DateValidationTest.prototype.testValidateIncorrectHourThreeDigits = function() {
    var validator = new DateValidator();
    assertEquals(false, validator.validate("12-12-2012", "123:12"));
};

DateValidationTest.prototype.testValidateIncorrectHourLetter = function() {
    var validator = new DateValidator();
    assertEquals(false, validator.validate("12-12-2012", "a:12"));
};

DateValidationTest.prototype.testValidateIncorrectDayThreeDigits = function() {
    var validator = new DateValidator();
    assertEquals(false, validator.validate("12-12-2012", "12:123"));
};

DateValidationTest.prototype.testValidateCorrect29Feb = function() {
    var validator = new DateValidator();
    assertEquals(true, validator.validate("29-02-2004", "12:12"));
};

DateValidationTest.prototype.testValidateIncorrect31Jun = function() {
    var validator = new DateValidator();
    assertEquals(false, validator.validate("31-06-2001", "12:12"));
};

DateValidationTest.prototype.testValidateCorrect31Jan = function() {
    var validator = new DateValidator();
    assertEquals(true, validator.validate("31-01-2001", "00:00"));
};

DateValidationTest.prototype.testValidateCorrect31Jan = function() {
    var validator = new DateValidator();
    assertEquals(false, validator.validate("31-01-2001", "24:00"));
};

DateValidationTest.prototype.testValidateCorrect31Jan = function() {
    var validator = new DateValidator();
    assertEquals(false, validator.validate("31-01-2001", "00:60"));
};

