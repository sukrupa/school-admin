class Talent
  
  attr_reader :talents
  
  def initialize comma_separated_talents
    if (!comma_separated_talents.nil? and !comma_separated_talents.strip.empty?)
      @talents = comma_separated_talents.split(",")
      @talents = @talents.map do |talent|
        talent.strip
      end
    else
      @talents = []
    end
  end
end
