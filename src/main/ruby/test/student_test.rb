require 'test/unit'
require 'student'

class StudentTest < Test::Unit::TestCase
  
  def test_should_return_non_nil_attributes
    student = Student.new :name => 'minno'
    assert_equal({'name' => 'minno'}, student.attributes)
  end
  
end
