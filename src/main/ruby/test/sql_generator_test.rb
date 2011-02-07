require 'test/unit'
require 'sql_generator'
require 'Student'
require 'rubygems'
require 'roo'

class SQLGeneratorTest < Test::Unit::TestCase
  
  def setup
    @generator = SQLGenerator.new
  end  
  
  def test_should_generate_insert_statement_for_student
    student = Student.new :name => 'minno'
    assert_equal(@generator.generate_sql(student), "INSERT INTO student (name) VALUES ('minno');")
  end
  
end
