package hezt.assesibilityevaluate.chainextractor.model.stg.input;

public class EdgeTag {

    private String typeOfUiElement;
    private String handlerMethod;
    private Integer resId;

    public String getTypeOfUiElement() {
        return typeOfUiElement;
    }

    public void setTypeOfUiElement(String typeOfUiElement) {
        this.typeOfUiElement = typeOfUiElement;
    }

    public String getHandlerMethod() {
        return handlerMethod;
    }

    public void setHandlerMethod(String handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public EdgeTag(String typeOfUiElement, String handlerMethod, Integer resId) {
        this.typeOfUiElement = typeOfUiElement;
        this.handlerMethod = handlerMethod;
        this.resId = resId;
    }

    @Override
    public String toString() {
        return String.valueOf(resId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (typeOfUiElement == null ? 0 : typeOfUiElement.hashCode());
        result = prime * result + (handlerMethod == null ? 0 : handlerMethod.hashCode());
        result = prime * result + (resId == null ? 0 : resId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;

        EdgeTag other = (EdgeTag) obj;
        if (resId == null) {
            return other.resId == null;
        } else if(!resId.equals(other.resId)){
            return false;
        } else if(handlerMethod == null){
            return other.handlerMethod == null;
        } else return handlerMethod.equals(other.handlerMethod);
    }
}
