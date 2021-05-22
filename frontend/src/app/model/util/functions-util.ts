import * as moment from 'moment';

export class FunctionsUtil {
    static validarEmail(email: string): boolean {
        if (this.isEmpty(email)) {
            return false;
        }

        // tslint:disable-next-line:max-line-length
        const regex = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
        return regex.test(email);
    }

    static removeAcentos(str) {
        return str
            .replace(/[ÀÁÂÃÄÅ]/g, 'A')
            .replace(/[àáâãäåâ]/g, 'a')
            .replace(/[ÈÉÊË]/g, 'E')
            .replace(/[^a-z0-9]/gi, '');

    }

    static isEmpty(text: string): boolean {
        return (!text || text.trim().length <= 0);
    }

    static isEmptyId(id: number): boolean {
        return (!id || id <= 0);
    }

    static validarCPF(cpf: string): boolean {
        if (this.isEmpty(cpf)) {
            return false;
        }

        // tslint:disable-next-line:max-line-length
        const regex = new RegExp(/([0-9]{2}[\.]?[0-9]{3}[\.]?[0-9]{3}[\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\.]?[0-9]{3}[\.]?[0-9]{3}[-]?[0-9]{2})/g);
        return regex.test(cpf);
    }

    static validarCNPJ(cnpj: string): boolean {
        if (this.isEmpty(cnpj)) {
            return false;
        }

        // tslint:disable-next-line:max-line-length
        const regex = new RegExp(/([0-9]{2}[\.]?[0-9]{3}[\.]?[0-9]{3}[\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\.]?[0-9]{3}[\.]?[0-9]{3}[-]?[0-9]{2})/g);
        return regex.test(cnpj);
    }

    static validarCEP(cep: string): boolean {
        if (this.isEmpty(cep)) {
            return false;
        }

        const regex = new RegExp(/[0-9]{5}-[0-9]{3}|[0-9]{8}/);
        return regex.test(cep);
    }

    static removerMascara(texto: string) {
        if (this.isEmpty(texto)) {
            return '';
        }

        const regex = new RegExp(/[^0-9a-zA-Z]/, 'g');
        return texto.replace(regex, '');
    }

    static parseDate(value: any): Date | null {

        if ((typeof value === 'string') && (value.indexOf('/') > -1)) {
            const str = value.split('/');

            const date = Number(str[0]);
            const month = Number(str[1]) - 1;
            const year = Number(str[2]);

            return new Date(year, month, date);
        } else if ((typeof value === 'string') && value === '') {
            return new Date();
        }
        const timestamp = typeof value === 'number' ? value : Date.parse(value);
        return isNaN(timestamp) ? null : new Date(timestamp);
    }

    static dateToString(value: string): string | null {
        return moment(new Date(FunctionsUtil.parseDate(value))).format();
    }

    static dateToFormat(value: string, format?: string): string | null {
        if (format == null) format = 'YYYY-MM-DD'; 
        return moment(new Date(FunctionsUtil.parseDate(value))).format(format);
    }


    // static formatarCNPJ(cnpj: string) {
    //     if (!this.isEmpty(cnpj)) {
    //         return new CnpjPipe().transform(cnpj);
    //     }
    // }

    // static formatarCPF(cpf: string) {
    //     if (!this.isEmpty(cpf)) {
    //         return new CpfPipe().transform(cpf);
    //     }
    // }

}
