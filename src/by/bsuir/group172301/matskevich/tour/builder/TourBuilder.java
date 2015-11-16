package by.bsuir.group172301.matskevich.tour.builder;

import by.bsuir.group172301.matskevich.tour.entity.Tour;
import by.bsuir.group172301.matskevich.tour.entity.TourType;
import by.bsuir.group172301.matskevich.tour.exception.BuildException;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Build a tour object from request
 */
public class TourBuilder extends EntityBuilder<Tour> {

    private Logger logger = Logger.getRootLogger();
    @Override
    public Tour build(Map<String, String[]> map) throws BuildException {
        Tour tour = new Tour();
        build(map, tour);

        return tour;
    }

    /**
     * Fill entity
     * @param map
     * @param tour
     * @throws BuildException
     */
    public void build(Map<String, String[]> map, Tour tour) throws BuildException{
        boolean tourname = !buildTourname(map.get("tourname"), tour);
        boolean price = !buildPrice(map.get("price"), tour);
        boolean details = !buildDetails(map.get("details"), tour);
        boolean discount = !buildDiscount(map.get("regular_discount"), tour);
        boolean hot = !buildHot(map.get("hot"), tour);
        boolean type = !buildType(map.get("type"), tour);

       
        if (tourname || details || price || discount || hot || type){
            throw new BuildException();
        }

    }

    /**
     * build tour Tourname
     * @param args parameter values
     * @param tour target tour
     */
    public boolean buildTourname(String[] args, Tour tour) {
        if (args != null && args.length > 0){
            String tourname = args[0];
            if (tourname.length() > 0){
                try {
                    tour.setTourname(new String(args[0].getBytes("ISO-8859-1"),"UTF-8"));

                } catch (UnsupportedEncodingException e) {
                    logger.error(e);
                }
                return true;
            }

        }
        return false;
    }


    /**
     * build tour Price
     * @param args parameter values
     * @param tour target tour
     */
    public boolean buildPrice(String[] args, Tour tour) {
        if (args != null && args.length > 0 && args[0].length() > 0){
            try{
                int price = Integer.parseInt(args[0]);
                if (price > 0) {
                    tour.setPrice(price);
                    return true;
                } else{
                    return false;
                }
            } catch (NumberFormatException e){
                return false;
            }
        }
        return false;
    }

    /**
     * build tour Discount
     * @param args parameter values
     * @param tour target tour
     */
    public boolean buildDiscount(String[] args, Tour tour) {
        if (args != null && args.length > 0 && args[0].length() > 0){
            try{
                int discount = Integer.parseInt(args[0]);
                if (discount >= 0 && discount < 100) {
                    tour.setRegularDiscount(discount);
                    return true;
                } else{
                    return false;
                }
            } catch (NumberFormatException e){
                return false;
            }
        }

        return false;
    }

    /**
     * build tour Hot
     * @param args parameter values
     * @param tour target tour
     */
    public boolean buildHot(String[] args, Tour tour) {
        if (args != null && args.length > 0 && args[0].length() > 0){
            tour.setHot(true);

        } else{
            tour.setHot(false);
        }
        return true;
    }

    /**
     * build tour Details
     * @param args parameter values
     * @param tour target tour
     */
    public boolean buildDetails(String[] args, Tour tour) {
        if (args != null && args.length > 0){
            String details = args[0];
            if (details.length() > 0){

                try {
                    tour.setDetails(new String(args[0].getBytes("ISO-8859-1"),"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    logger.error(e);
                }

                return true;
            }

        }
        return false;
    }

    /**
     * build tour Type
     * @param args parameter values
     * @param tour target tour
     */
    public boolean buildType(String[] args, Tour tour) {
        if (args != null && args.length > 0 && args[0].length() > 0){
            try{
                int typeID = Integer.parseInt(args[0]);
                TourType type = TourType.findById(typeID);
                if (type != null){
                    tour.setType(type);
                    return true;
                } else{
                    return false;
                }
            } catch (NumberFormatException e){
                return false;
            }
        }

        return false;
    }
}
