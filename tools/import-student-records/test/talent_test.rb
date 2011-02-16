require 'test/unit'
require 'talent'

class TalentTest < Test::Unit::TestCase
  
  def test_should_return_right_amount_of_talents
    talents = Talent.new("Sports,Art")
    assert_equal(2,talents.talents.length)
  end
  
  def test_should_return_nil_talent_list
    talents = Talent.new(" ")
    assert_equal([], talents.talents)
  end
  
  def test_should_return_correct_value
    talents = Talent.new("Sports,Art,Drawing")
    assert_equal("Arts & Crafts", talents.talents[1])
  end
  
end
