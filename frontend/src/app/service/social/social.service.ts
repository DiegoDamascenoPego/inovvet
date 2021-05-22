import { Injectable } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';

export class MetaTag {
   name: string;
   value: string;
   isFacebook: boolean;

   constructor(name: string, value: string, isFacebook: boolean) {
      this.name = name;
      this.value = value;
      this.isFacebook = isFacebook;
   }
}

@Injectable({
   providedIn: 'root'
})
export class SocialService {

   private urlMeta: string = "og:url";
   private titleMeta: string = "og:title";
   private descriptionMeta: string = "og:description";
   private imageMeta: string = "og:image";
   private imageWidth: string = "og:image:width";
   private imageHeight: string = "og:image:height";
   private fbId: string = "fb:app_id";
   private secureImageMeta: string = "og:image:secure_url";
   private twitterTitleMeta: string = "twitter:text:title";
   private twitterImageMeta: string = "twitter:image";

   constructor(private titleService: Title, private metaService: Meta) {

   }

   public setTitle(title: string): void {
      this.titleService.setTitle(title);
   }

   public setSocialMediaTags(url: string, title: string, description: string, image: string): void {
      var imageUrl = image;
      var tags = [
         new MetaTag(this.fbId, '581648679139235', true),
         new MetaTag(this.urlMeta, url, true),
         new MetaTag(this.titleMeta, title, true),
         new MetaTag(this.descriptionMeta, description, true),
         new MetaTag(this.imageMeta, imageUrl, true),
         new MetaTag(this.secureImageMeta, imageUrl, true),
         new MetaTag(this.imageWidth, '400', true),
         new MetaTag(this.imageHeight, '400', true),
         new MetaTag(this.twitterTitleMeta, title, false),
         new MetaTag(this.twitterImageMeta, imageUrl, false)
      ];
      this.setTags(tags);
   }

   private setTags(tags: MetaTag[]): void {
      tags.forEach(siteTag => {
         const tag = siteTag.isFacebook ? this.metaService.getTag(`property='${siteTag.name}'`) : this.metaService.getTag(`name='${siteTag.name}'`);
         if (siteTag.isFacebook) {
            this.metaService.updateTag({ property: siteTag.name, content: siteTag.value });
         } else {
            this.metaService.updateTag({ name: siteTag.name, content: siteTag.value });
         }
      });
   }
}
