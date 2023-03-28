import { Swiper, SwiperSlide} from "swiper/react";
import {content} from '../Content';

import { Keyboard, Pagination, EffectCoverflow} from "swiper";
import InnerImageZoom from 'react-inner-image-zoom';

//swiper css
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/effect-coverflow";
import 'react-inner-image-zoom/lib/InnerImageZoom/styles.min.css';

const Gallery = ()=>{
    const {onlineKitchen} = content;
    return(
        <div className="container">
            <div className="row">
                <div className="head-section">
                    <h1>{onlineKitchen.title}</h1>
                    <p>{onlineKitchen.sub_title}</p>
                </div>
                <Swiper pagination={{
                        clickable: true,
                        }}
                        loop = {true}
                        touchEventsTarget = 'wrapper'                            
                        slidesPerView = {3}
                        spaceBetween={40}
                        keyboard={{
                            enabled: true,
                            onlyInViewport: false,
                            }}                        
                        speed={4500}
                        centeredSlides= {true}
                        grabCursor= {true}
                        effect= 'coverflow'
                        coverflowEffect= {{
                            rotate: 50,
                            stretch: 80,
                            depth: 300,
                            modifier: 1,
                            slideShadows: false,
                        }}              
                        modules={[Pagination, Keyboard, EffectCoverflow]}>

                {onlineKitchen.img_slides.map((content, i) =>(
                    <SwiperSlide key={i}>
                        <InnerImageZoom src={content.image_url} alt={`"image_number" ${i}`} />
                    </SwiperSlide>
                ))}
                
                </Swiper>
                <div className="details">
                    <p>Type: <span>{onlineKitchen.type}</span></p>
                    <p>Technology:</p>
                    {onlineKitchen.technology.map((content, i) =>(
                        <div className="tech-label">
                            <p>{content.techName}</p>
                        </div>
                    ))}
                    <p>Case Study: <span>{onlineKitchen.caseStudy}</span></p>
                    <p>Link: <a href={onlineKitchen.link}></a></p>
                    <p>Note: <span>{onlineKitchen.note}</span></p>
                </div>
            </div>
        </div>
    );

}

export default Gallery;