require 'test/unit'
require 'sql_generator'
require 'student'
require 'rubygems'
require 'roo'

class SQLGeneratorTest < Test::Unit::TestCase
  
  def setup
    @generator = SQLGenerator.new
  end  
  
  def test_should_generate_insert_statement
    student = Student.new :name => 'minno', :date_of_birth => '15/10/2004', :gender => 'female'
    assert_equal("INSERT INTO student (name,gender,date_of_birth) VALUES ('minno','female','2004-10-15');", @generator.generate_sql([student]).first)
  end
  
  def test_should_be_able_to_cope_with_missing_values
    student = Student.new :name => 'minno'
    assert_equal("INSERT INTO student (name) VALUES ('minno');", @generator.generate_sql([student]).first)
  end
  
  def test_should_generate_multiple_insert_statements
    minno = Student.new :name => 'minno'
    pat = Student.new :name => 'pat'
  
    sql_statements = @generator.generate_sql([minno, pat])
  
    assert_equal("INSERT INTO student (name) VALUES ('minno');", sql_statements[0])
    assert_equal("INSERT INTO student (name) VALUES ('pat');", sql_statements[1])
  end
  
end
