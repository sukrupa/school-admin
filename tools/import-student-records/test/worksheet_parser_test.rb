require 'test/unit'
require 'worksheet_parser'
require 'student'
require 'roo'
# require 'rubygems'

class TestWorksheetParser < Test::Unit::TestCase
  
  def setup
    @worksheet = Excelx.new('test/Test.xlsx')
    @worksheet.default_sheet = 'happy-path'
    @worksheet_parser = WorksheetParser.new(@worksheet)
  end  
  
  def test_starting_corner
    assert_equal(5,@worksheet_parser.starting_corner)
  end
  
  def test_parse
    assert_equal(2, @worksheet_parser.parse.length)
  end
  
  def test_parse_value
    students = @worksheet_parser.parse
    assert_equal(students.length, 2)
    student = students.first[0]
    assert_equal("Arumugam.P", student.name)
    assert_equal("Male", student.gender)
    assert_equal("2003-06-19", student.date_of_birth)        
  end
  
  def test_should_cope_with_offset_columns
    @worksheet.default_sheet = 'offset-columns'
    @worksheet_parser = WorksheetParser.new(@worksheet)
    students = @worksheet_parser.parse
    student = students.first[0]
    assert_equal("Minno", student.name)
    assert_equal("Female", student.gender)
    assert_equal("1999-12-25", student.date_of_birth)            
  end
  
  
  
end
