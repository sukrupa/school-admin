require 'test/unit'
require 'worksheet_parser'
require 'student'
require 'roo'
# require 'rubygems'

class TestWorksheetParser < Test::Unit::TestCase
  
  def setup
    @worksheet = Excelx.new('Test.xlsx')
    @worksheet.default_sheet = @worksheet.sheets.first
    @worksheet_parser = WorksheetParser.new(@worksheet)
  end  
  
  def test_starting_corner
    assert_equal(5,@worksheet_parser.starting_corner)
  end
  
  def test_skipped_columns
    assert_equal([5,7], @worksheet_parser.skipped_columns)
  end
  
  def test_parse
    assert_equal(2, @worksheet_parser.parse.length)
  end
  
  def test_parse_value
    student = @worksheet_parser.parse.first
    assert_equal("Arumugam.P", student.name)
    assert_equal("Male", student.gender)
    assert_equal("2003-06-19", student.date_of_birth)        
  end
  
end
