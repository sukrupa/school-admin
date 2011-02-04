require 'test/unit'
require 'WorksheetParser'
require 'Student'
require 'rubygems'
require 'roo'

class TestWorksheetParser < Test::Unit::TestCase
  
  def setup
    @students = Excelx.new("Test.xlsx")
    @students.default_sheet = @students.sheets.first
    @worksheet_parser = WorksheetParser.new(@students)
  end  
  
  def test_starting_corner
    assert_equal(@worksheet_parser.starting_corner,5)
  end
  
  def test_skipped_columns
    assert_equal(@worksheet_parser.skipped_columns,[5,7])
  end
  
  def test_parse
    assert_equal(@worksheet_parser.parse.length,2)
  end
  
  def test_parse_value
    students_data = @worksheet_parser.parse
    student = "1,Arumugam.P,Male,Mr.Paneer Selvam,Mrs.Krpagam,19/06/2003,-"
    assert_equal(students_data[0].to_s,student)
  end
  
end
