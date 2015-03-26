
package Entity;

/**
 *
 * @author peter
 */
public class PizzaList {
    

        String name ;
        String desc ;
        Double price;       

        @Override
        public String toString()
        {
            return "A pizza : " + this.getName() + " " + this.getDesc()  + " " + Double.toString(getPrice());
        }

        /**
         * @return the desc
         */
        public String getDesc() {
            return desc;
        }

        /**
         * @param desc the desc to set
         */
        public void setDesc(String desc) {
            this.desc = desc;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the price
         */
        public Double getPrice() {
            return price;
        }

        /**
         * @param price the price to set
         */
        public void setPrice(Double price) {
            this.price = price;
        }
    }